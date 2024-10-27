package wx82.agrotech.groweasyapi.iot;

import org.springframework.stereotype.Service;
import wx82.agrotech.groweasyapi.user.User;

@Service
public class SensorMapper {

    public Sensor toSensor(SensorRequest request){
        return Sensor.builder()
                .temperature(request.temperature())
                .humidity(request.humidity())
                .luminosity(request.luminosity())
                .user(User.builder()
                        .id(request.idUser())
                        .build())
                .build();
    }

    public static SensorResponse toSensorResponse(SensorResponse sensor){
        return SensorResponse.builder()
                .temperature(sensor.getTemperature())
                .humidity(sensor.getHumidity())
                .luminosity(sensor.getLuminosity())
                .build();
    }
}
