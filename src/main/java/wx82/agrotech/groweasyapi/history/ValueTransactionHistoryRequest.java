package wx82.agrotech.groweasyapi.history;
public record ValueTransactionHistoryRequest(
        Double value,
        String unitOfMeasure,
        Integer deviceId
) {
}
