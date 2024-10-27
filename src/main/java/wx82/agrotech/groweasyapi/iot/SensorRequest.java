package wx82.agrotech.groweasyapi.iot;

public record SensorRequest(
        Double temperature,
        Double humidity,
        Double luminosity,
        Integer idUser
) {
}
