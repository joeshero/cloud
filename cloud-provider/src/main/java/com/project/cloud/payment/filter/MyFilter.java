package com.project.cloud.payment.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author Qiao guorui
 * @Date 2021/1/24 7:26 下午
 */
@Component
@Slf4j
public class MyFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //拦截方法，拦截到请求后，自动执行
        log.info("filter 拦截方法，拦截到请求后，自动执行 ");
        //以后开发中，不会将 user对象存到session，只会在地址上带上token
        //根据token是否有空可以判断是否登录
        //http://localhost:8001/users/3?token=10001&pageSize=30
        String token =  exchange.getRequest().getQueryParams().getFirst("token");
        if(token == null || "".equals(token)){
            //未登录 跳转到登录页
            log.info("----跳到登录页面");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);//2 全部放行
    }

    @Override
    public int getOrder() {
        //3:系统调用该方法获得过滤器的优先级
        return 1; //数字越小，越优先
    }
}