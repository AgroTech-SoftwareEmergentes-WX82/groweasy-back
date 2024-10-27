package wx82.agrotech.groweasyapi.sensor;

public record LatestSensorValueRequest(
        Double temperature,
        Double humidity,
        Double luminosity,
        Integer userId
) {
}