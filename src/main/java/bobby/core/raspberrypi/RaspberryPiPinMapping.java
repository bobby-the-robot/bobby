package bobby.core.raspberrypi;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

import java.util.HashMap;
import java.util.Map;

public class RaspberryPiPinMapping {

    public static final Map<Integer, Pin> PIN_MAPPING = Map.ofEntries(
            new HashMap.SimpleImmutableEntry<>(0, RaspiPin.GPIO_00),
            new HashMap.SimpleImmutableEntry<>(1, RaspiPin.GPIO_01),
            new HashMap.SimpleImmutableEntry<>(2, RaspiPin.GPIO_02),
            new HashMap.SimpleImmutableEntry<>(3, RaspiPin.GPIO_03),
            new HashMap.SimpleImmutableEntry<>(4, RaspiPin.GPIO_04),
            new HashMap.SimpleImmutableEntry<>(5, RaspiPin.GPIO_05),
            new HashMap.SimpleImmutableEntry<>(6, RaspiPin.GPIO_06),
            new HashMap.SimpleImmutableEntry<>(7, RaspiPin.GPIO_07),
            new HashMap.SimpleImmutableEntry<>(8, RaspiPin.GPIO_08),
            new HashMap.SimpleImmutableEntry<>(9, RaspiPin.GPIO_09),
            new HashMap.SimpleImmutableEntry<>(10, RaspiPin.GPIO_10),
            new HashMap.SimpleImmutableEntry<>(11, RaspiPin.GPIO_11),
            new HashMap.SimpleImmutableEntry<>(12, RaspiPin.GPIO_12),
            new HashMap.SimpleImmutableEntry<>(13, RaspiPin.GPIO_13),
            new HashMap.SimpleImmutableEntry<>(14, RaspiPin.GPIO_14),
            new HashMap.SimpleImmutableEntry<>(15, RaspiPin.GPIO_15),
            new HashMap.SimpleImmutableEntry<>(16, RaspiPin.GPIO_16),
            new HashMap.SimpleImmutableEntry<>(17, RaspiPin.GPIO_17),
            new HashMap.SimpleImmutableEntry<>(18, RaspiPin.GPIO_18),
            new HashMap.SimpleImmutableEntry<>(19, RaspiPin.GPIO_19),
            new HashMap.SimpleImmutableEntry<>(20, RaspiPin.GPIO_20),
            new HashMap.SimpleImmutableEntry<>(21, RaspiPin.GPIO_21),
            new HashMap.SimpleImmutableEntry<>(22, RaspiPin.GPIO_22),
            new HashMap.SimpleImmutableEntry<>(23, RaspiPin.GPIO_23),
            new HashMap.SimpleImmutableEntry<>(24, RaspiPin.GPIO_24),
            new HashMap.SimpleImmutableEntry<>(25, RaspiPin.GPIO_25),
            new HashMap.SimpleImmutableEntry<>(26, RaspiPin.GPIO_26),
            new HashMap.SimpleImmutableEntry<>(27, RaspiPin.GPIO_27),
            new HashMap.SimpleImmutableEntry<>(28, RaspiPin.GPIO_28),
            new HashMap.SimpleImmutableEntry<>(29, RaspiPin.GPIO_29),
            new HashMap.SimpleImmutableEntry<>(30, RaspiPin.GPIO_30),
            new HashMap.SimpleImmutableEntry<>(31, RaspiPin.GPIO_31)
    );
}
