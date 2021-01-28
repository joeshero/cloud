package com.project.cloud.payment.config;

import com.project.cloud.payment.filter.MyFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Qiao guorui
 * @Date 2021/1/22 12:33 上午
 */
//@Configuration
public class GatewayConfig {

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
//        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
////        routes.route("path_route_angenin",
////                    r -> r.path("/test").uri("https://blog.csdn.net/qq_36903261/article/details/106635918"));
//        routes.route("path_route_angenin",  //id
//                r -> r.path("/guonei")  //访问 http://localhost:9527/guonei
//                        .uri("http://news.baidu.com/guoji"));
//        routes.route("payment_route",  //id
//                r -> r.path("/payment/lb/**")  //访问 http://localhost:9527/guonei
//                        .uri("lb://cloud-payment-service"));
//        return routes.build();
//    }
}
