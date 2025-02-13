package initialize

import (
	"context"
	"encoding/json"
	"fmt"
	"log"
	"strings"

	"example.com/send_mail/global"
	"example.com/send_mail/model"
)

// reader and process
func ReaderAndProcess() {
	r := global.ReaderOTPAuth
	for {
		m, err := r.ReadMessage(context.Background())
		if err != nil {
			log.Fatal("failed to read message:", err)
			break
		}
		
		key := strings.TrimSpace(string(m.Key))
		var value model.Message
		if err := json.Unmarshal(m.Value, &value); err != nil {
			log.Fatal("failed to unmarshal message:", err)
			continue
		}

		// Process message here
        fmt.Printf("Consumed message: %s, Key: %s, Value: %+v\n", string(m.Topic), key, value)
	}
	
	if err := r.Close(); err != nil {
		log.Fatal("failed to close reader:", err)
	}
}