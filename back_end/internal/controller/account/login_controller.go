package account

import (
	"example.com/be/global"
	"example.com/be/internal/model"
	"example.com/be/internal/service"
	"example.com/be/response"
	"github.com/gin-gonic/gin"
	"go.uber.org/zap"
)

var Login = new(cUserLogin)

type cUserLogin struct {
}

// Login godoc
// @Summary      Login user
// @Description  Login user by account and password
// @Tags         accounts management
// @Accept       json
// @Produce      json
// @Param        payload body model.LoginInput true "payload"
// @Success      200  {object}  response.ResponseData
// @Failure      500  {object}  response.ErrResponseData
// @Router       /v1/user/login [post]
func (cU *cUserLogin) Login(c *gin.Context) {

	var params model.LoginInput
	if err := c.ShouldBindJSON(&params); err != nil {
		response.ErrorResponse(c, response.ErrCodeBindLoginInput, err.Error())
		return
	}

	codeStatus, out, err := service.UserLogin().Login(c, &params)
	if err != nil {
		global.Logger.Error("Error login user", zap.Error(err))
		response.ErrorResponse(c, codeStatus, err.Error())
		return
	}

	response.SuccessResponse(c, response.ErrCodeSuccess, out)
}

// Register godoc
// @Summary      Register a new account
// @Description  When user register, system will send OTP to user's phone number or email address
// @Tags         accounts management
// @Accept       json
// @Produce      json
// @Param        payload body model.RegisterInput true "payload"
// @Success      200  {object}  response.ResponseData
// @Failure      500  {object}  response.ErrResponseData
// @Router       /v1/user/register [post]
func (cU *cUserLogin) Register(c *gin.Context) {
	var params model.RegisterInput
	if err := c.ShouldBindJSON(&params); err != nil {
		response.ErrorResponse(c, response.ErrCodeBindRegisterInput, err.Error())
		return
	}

	codeStatus, err := service.UserLogin().Register(c, &params)
	if err != nil {
		global.Logger.Error("Error registering user otp", zap.Error(err))
		response.ErrorResponse(c, codeStatus, err.Error())
		return
	}

	response.SuccessResponse(c, response.ErrCodeSuccess, nil)
}

// Verify login by user
// @Summary      Verify OTP bu user when register
// @Description  Verify OTP bu user when register
// @Tags         accounts management
// @Accept       json
// @Produce      json
// @Param        payload body model.RegisterInput true "payload"
// @Success      200  {object}  response.ResponseData
// @Failure      500  {object}  response.ErrResponseData
// @Router       /v1/user/verify_account [post]
func (cU *cUserLogin) VerifyOTP(c *gin.Context) {
	var params model.VerifyInput
	if err := c.ShouldBindJSON(&params); err != nil {
		response.ErrorResponse(c, response.ErrCodeBindVerifyInput, err.Error())
		return
	}

	out, err := service.UserLogin().VerifyOTP(c, &params)
	if err != nil {
		global.Logger.Error("Error verifying user otp", zap.Error(err))
		response.ErrorResponse(c, response.ErrCodeVerifyOTPFail, err.Error())
		return
	}

	response.SuccessResponse(c, response.ErrCodeSuccess, out)
}

// Update password when register
// @Summary      update password when register
// @Description  after verification otp can be updated password
// @Tags         accounts management
// @Accept       json
// @Produce      json
// @Param        payload body model.UpdatePasswordInput true "payload"
// @Success      200  {object}  response.ResponseData
// @Failure      500  {object}  response.ErrResponseData
// @Router       /v1/user/upgrade_password_register [post]
func (cU *cUserLogin) UpgradePasswordRegister(c *gin.Context) {
	var params model.UpdatePasswordInput
	if err := c.ShouldBindJSON(&params); err != nil {
		response.ErrorResponse(c, response.ErrCodeBindUpdatePasswordInput, err.Error())
		return
	}

	out, err := service.UserLogin().UpdatePasswordRegister(c, &params)
	if err != nil {
		global.Logger.Error("Error updating password register", zap.Error(err))
		response.ErrorResponse(c, response.ErrCodeUpdatePasswordRegister, err.Error())
		return
	}

	response.SuccessResponse(c, response.ErrCodeSuccess, out)
}
