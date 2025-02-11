package initialize

import (
	"example.com/be/global"
	"github.com/segmentio/kafka-go"
	"go.uber.org/zap"
)

// Ininitialize kafka producer
var KafkaProducer *kafka.Writer

// Initial kafka
func InitKafka() {
	KafkaProducer = &kafka.Writer{
		Addr:     kafka.TCP("localhost:9094"),
		Topic:    "otp-auth-topic",
		Balancer: &kafka.LeastBytes{},
	}

	global.KafkaProducer = KafkaProducer

}

// close kafka producer
func CloseKafka() {
	err := KafkaProducer.Close()
	if err != nil {
		global.Logger.Error("Error closing Kafka producer", zap.Error(err))
	}
}
