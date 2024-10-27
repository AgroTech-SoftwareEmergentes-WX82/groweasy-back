package wx82.agrotech.groweasyapi.sensor;

import wx82.agrotech.groweasyapi.iot.Sensor;
import wx82.agrotech.groweasyapi.iot.SensorRequest;
import wx82.agrotech.groweasyapi.iot.SensorResponse;
import wx82.agrotech.groweasyapi.user.User;

import java.time.LocalDateTime;

public class LatestSensorValueMapper {

    public LatestSensorValue toSensor(LatestSensorValueRequest request){
        return LatestSensorValue.builder()
                .temperature(request.temperature())
                .humidity(request.humidity())
                .luminosity(request.luminosity())
                .id(request.userId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static LatestSensorValueResponse toSensorResponse(LatestSensorValueResponse sensor){
        return LatestSensorValueResponse.builder()
                .temperature(sensor.getTemperature())
                .humidity(sensor.getHumidity())
                .luminosity(sensor.getLuminosity())
                .updatedAt(sensor.getUpdatedAt())
                .build();
    }
}
