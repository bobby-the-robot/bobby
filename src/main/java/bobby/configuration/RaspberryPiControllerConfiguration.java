package bobby.configuration;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RaspberryPiControllerConfiguration {

    @Bean
    public GpioController gpioController() {
        return GpioFactory.getInstance();
    }
}
