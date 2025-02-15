package config

// Config structure
type Config struct {
	SendGrid SendGridSetting `mapstructure:"sendgird"`
	Kafka    KafkaSetting    `mapstructure:"kafka"`
}

// Send Grid Setting Structure
type SendGridSetting struct {
	APIKey string `mapstructure:"api_key"`
}

// Kafka Setting Structure
type KafkaSetting struct {
	BootstraperSeverMail string `mapstructure:"bootstrap_server_mail"`
}
