package main

import "example.com/send_mail/initialize"

func main() {
	initialize.Initialize()

	// Read and process messages from Kafka
	initialize.ReaderAndProcess()
}