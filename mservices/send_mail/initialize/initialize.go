package initialize

import (
	"example.com/send_mail/global"
	"example.com/send_mail/utils/kafka_reader"
	"example.com/send_mail/consts")

// func initialize
func Initialize() {
	global.ReaderOTPAuth = kafkareader.GetKafkaReader(
		consts.TOPIC_GO_SEND_MAIL_OTP, 
		"localhost:9094", 
		"gr-01",
	)
}