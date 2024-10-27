package wx82.agrotech.groweasyapi.history;

import org.springframework.stereotype.Service;
import wx82.agrotech.groweasyapi.device.Device;
import wx82.agrotech.groweasyapi.statistics.StaticsSensor;

@Service
public class ValueTransactionHistoryMapper {

    public ValueTransactionHistory toValueTransactionHistory(ValueTransactionHistoryRequest request) {
        return ValueTransactionHistory.builder()
                .value(request.value())
                .unitOfMeasure(request.unitOfMeasure())
                .device(Device.builder()
                        .id(request.deviceId())
                        .build()
                )
                .build();
    }

    public ValueTransactionHistoryResponse toValueTransactionHistoryResponse(ValueTransactionHistory valueResponse) {
        return ValueTransactionHistoryResponse.builder()
                .id(valueResponse.getId())
                .value(valueResponse.getValue())
                .unitOfMeasure(valueResponse.getUnitOfMeasure())
                .createdAt(valueResponse.getCreatedDate())
                .build();
    }

    public StaticsSensor toStaticsSenor(ValueTransactionHistory valueResponse){
        return StaticsSensor.builder()
                .build();
    }

}
