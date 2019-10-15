package ratings.topics;

/*
@Configuration
public class KafkaTopics {
    public static final String RATING_TOPIC = "rating";

    @Value("${kafka.host}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic bookTopic() {
        return new NewTopic(RATING_TOPIC, 3, (short) 1);
    }
}
*/