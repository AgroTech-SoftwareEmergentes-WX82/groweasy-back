package wx82.agrotech.groweasyapi.sensor;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LatestSensorValueService {

    private final LatestSensorValueRepository latestSensorValueRepository;

    /*public LatestSensorValue getLatestSensorValue(LatestSensorValueRequest request) {

    }*/


}
