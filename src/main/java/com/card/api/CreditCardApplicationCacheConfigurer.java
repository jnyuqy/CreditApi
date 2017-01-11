package com.card.api;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * 缓存配置，采用ehcache缓存机制<br>
 * <p> @Cacheable：负责将方法的返回值加入到缓存中,@Cacheable 支持如下几个参数：
value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name
key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL
condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL <p><br>
 * <p> @CacheEvict：负责清除缓存@CacheEvict 支持如下几个参数：
value：缓存位置名称，不能为空，同上
key：缓存的key，默认为空，同上
condition：触发条件，只有满足条件的情况才会清除缓存，默认为空，支持SpEL
allEntries：true表示清除value中的全部缓存，默认为false <p><br>
 * <p>  <p><br>
 * 
 * 项目名称：CreditCard<br>
 * 项目版本：V1.0 <br>
 * 类名称：CreditCardApplicationCacheConfigurer <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月16日 下午4:50:02 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月16日 下午4:50:02 <br>
 * 修改备注：<br>
 */
@Configuration
@EnableCaching // 标注启动缓存.
public class CreditCardApplicationCacheConfigurer {

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
       return new EhCacheCacheManager(bean.getObject());
    }

    /*
     * 据shared与否的设置, Spring分别通过CacheManager.create() 或new CacheManager()方式来创建一个ehcache基地.
     * 也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享)
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
     EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
      cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("ehcache.xml"));
      cacheManagerFactoryBean.setShared(true);
      return cacheManagerFactoryBean;
    }
}
