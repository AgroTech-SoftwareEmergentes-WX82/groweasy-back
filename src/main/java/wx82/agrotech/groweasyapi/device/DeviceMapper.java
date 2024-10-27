package wx82.agrotech.groweasyapi.device;

import org.springframework.stereotype.Service;

@Service
public class DeviceMapper {

    public Device toDevice(DeviceRequest request){
        return Device.builder()
                .name(request.name())
                .typeDevice(request.typeDevice())
                .topic(request.topic())
                .build();
    }

    public static DeviceResponse toDeviceResponse(Device device){
        return DeviceResponse.builder()
                .id(device.getId())
                .name(device.getName())
                .typeDevice(device.getTypeDevice())
                .topic(device.getTopic())
                .build();
    }
}
