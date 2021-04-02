package bobby.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("motion.wheel.pin")
public class WheelConfigurationProperties {

    private Side right;
    private Side left;

    @Data
    public static class Side {

        private int forward;
        private int back;
    }
}
