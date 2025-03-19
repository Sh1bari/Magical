package ru.noxly.efs.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import static java.time.Duration.ofMillis;
import static ru.noxly.efs.common.Constants.WEB_CLIENT_AUTH_BEAN;
import static ru.noxly.efs.common.Constants.WEB_CLIENT_GUILD_BEAN;

@Configuration
public class WebClientConfig {

    @Value("${services.guild.url}")
    private String guildBaseUrl;

    @Value("${services.guild.timeOut}")
    private Integer guildTimeOut;

    @Value("${services.auth.url}")
    private String authBaseUrl;

    @Value("${services.auth.timeOut}")
    private Integer authTimeOut;

    @Bean(WEB_CLIENT_AUTH_BEAN)
    public WebClient authWebClientSocket() {
        val httpClient = HttpClient.create().responseTimeout(ofMillis(authTimeOut));
        return WebClient
                .builder()
                .baseUrl(authBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean(WEB_CLIENT_GUILD_BEAN)
    public WebClient mainWebClientSocket() {
        val httpClient = HttpClient.create().responseTimeout(ofMillis(guildTimeOut));
        return WebClient
                .builder()
                .baseUrl(guildBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
