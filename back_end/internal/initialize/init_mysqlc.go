package initialize

import (
	"database/sql"
	"fmt"

	"example.com/be/global"
	"go.uber.org/zap"
)

// Handle err panic
func checkErrorPanicC(err error, errString string) {
	if err != nil {
		global.Logger.Error(errString, zap.Error(err))
		panic(err)
	}
}

// Initial my sql
func InitMysqlC() {
	m := global.Config.MySQL

	dsn := "%s:%s@tcp(%s:%v)/%s?charset=utf8mb4&parseTime=True&loc=Local"
	var s = fmt.Sprintf(dsn, m.Username, m.Password, m.Host, m.Port, m.Dbname)

	db, err := sql.Open("mysql", s)

	checkErrorPanicC(err, "SQLC connection failed")

	global.Logger.Info("SQLC connection successful")
	global.Mdbc = db

	// set Pool
	SetPoolC()
}

// InitMysql().SetPool()
func SetPoolC() {
	// m := global.Config.MySQL

	// sqlDb, err := global.Mdb.DB()
	// if err != nil {
	// 	fmt.Println("MySQL error: %s::", err)
	// 	global.Logger.Error("SetPool error", zap.Error(err))
	// }

	// sqlDb.SetConnMaxIdleTime(time.Duration(m.MaxIdleConns))    // Thoi gian toi da ket noi nhan doi -> Phuc hoi ket noi
	// sqlDb.SetMaxOpenConns(m.MaxOpenConns)                      // Gioi han so luong ket noi toi da
	// sqlDb.SetConnMaxLifetime(time.Duration(m.ConnMaxLifetime)) // Gioi han thoi gian toi da cua ket noi

	// TODO
}
