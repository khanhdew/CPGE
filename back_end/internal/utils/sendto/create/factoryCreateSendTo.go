package create

import (
	"example.com/be/internal/utils/sendto"
	"example.com/be/internal/utils/sendto/sendgrid"
	"example.com/be/internal/utils/sendto/smtp"
)

// Factory create struct for sending email
func FactoryCreateSendTo(
	typeSendTo string,
) sendto.ISendTo {
	switch typeSendTo {
	case sendto.TYPE_API:
		return sendto.NewSendToWithApi()
	case sendto.TYPE_SMTP:
		return smtp.NewSendToWithSMTP()
	case sendto.TYPE_SENDGRID:
		return sendgrid.NewSendToWithSendGrid()
	default:
		return nil
	}
}
