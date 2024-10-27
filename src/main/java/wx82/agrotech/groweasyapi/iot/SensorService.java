package wx82.agrotech.groweasyapi.iot;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import wx82.agrotech.groweasyapi.device.Device;
import wx82.agrotech.groweasyapi.device.DeviceRequest;
import wx82.agrotech.groweasyapi.user.User;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    public Integer save(SensorRequest request){
        Sensor sensor = sensorMapper.toSensor(request);
        return sensorRepository.save(sensor).getId();
    }

    public Double getLastTemperature(Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal());
        return sensorRepository.findLastTemperatureByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Sensor not found"));
    }

    public Double getLastHumidity(Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal());
        return sensorRepository.findLastHumidityByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Sensor not found"));
    }

    public Double getLastLuminosity(Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal());
        return sensorRepository.findLastLuminosityByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Sensor not found"));
    }

}
