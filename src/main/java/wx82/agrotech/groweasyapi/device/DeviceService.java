package wx82.agrotech.groweasyapi.device;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import wx82.agrotech.groweasyapi.common.PageResponse;
import wx82.agrotech.groweasyapi.exception.ResourceNotFoundException;
import wx82.agrotech.groweasyapi.user.User;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public Integer save(DeviceRequest request, Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal());
        Device device = deviceMapper.toDevice(request);
        device.setUser(user);
        return deviceRepository.save(device).getId();
    }

    public List<DeviceResponse> findAllDevices(Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal());
        List<Device> devices = deviceRepository.findAllDevices(user.getId());
        return devices.stream()
                .map(DeviceMapper::toDeviceResponse)
                .toList();
    }

    @Transactional
    public void delete(Integer deviceId, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));
        deviceRepository.delete(device);
        log.info("Event with ID:: {} deleted successfully", deviceId);
    }


}
