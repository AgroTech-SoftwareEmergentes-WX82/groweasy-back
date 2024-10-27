package wx82.agrotech.groweasyapi.history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ValueTransactionHistoryService {

    private final ValueTransactionHistoryRepository valueRepository;
    private final ValueTransactionHistoryMapper valueTransactionHistoryMapper;

    public Integer save(ValueTransactionHistoryRequest request) {
        ValueTransactionHistory valueHistory = valueTransactionHistoryMapper.toValueTransactionHistory(request);
        return valueRepository.save(valueHistory).getId();
    }

    public List<ValueTransactionHistoryResponse> findAllValuesHistory(Integer idDevices){
        List<ValueTransactionHistory> devices = valueRepository.findAllByTransactionId(idDevices);
        return devices.stream()
                .map(valueTransactionHistoryMapper::toValueTransactionHistoryResponse)
                .toList();
    }


}
