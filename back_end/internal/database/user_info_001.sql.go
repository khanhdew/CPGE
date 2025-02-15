// Code generated by sqlc. DO NOT EDIT.
// versions:
//   sqlc v1.27.0
// source: user_info_001.sql

package database

import (
	"context"
	"database/sql"
)

const addUserAutoUserId = `-- name: AddUserAutoUserId :execresult
INSERT INTO ` + "`" + `user_info_001` + "`" + ` (
    user_account, user_nickname, user_avatar, 
    user_state, user_mobile, user_gender, 
    user_birthday, user_email, user_is_authentication)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
`

type AddUserAutoUserIdParams struct {
	UserAccount          string
	UserNickname         sql.NullString
	UserAvatar           sql.NullString
	UserState            uint8
	UserMobile           sql.NullString
	UserGender           sql.NullInt16
	UserBirthday         sql.NullTime
	UserEmail            sql.NullString
	UserIsAuthentication uint8
}

func (q *Queries) AddUserAutoUserId(ctx context.Context, arg AddUserAutoUserIdParams) (sql.Result, error) {
	return q.db.ExecContext(ctx, addUserAutoUserId,
		arg.UserAccount,
		arg.UserNickname,
		arg.UserAvatar,
		arg.UserState,
		arg.UserMobile,
		arg.UserGender,
		arg.UserBirthday,
		arg.UserEmail,
		arg.UserIsAuthentication,
	)
}

const addUserHaveUserId = `-- name: AddUserHaveUserId :execresult
INSERT INTO ` + "`" + `user_info_001` + "`" + ` (
    user_id, user_account, user_nickname, 
    user_avatar, user_state, user_mobile, 
    user_gender, user_birthday, user_email, 
    user_is_authentication)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
`

type AddUserHaveUserIdParams struct {
	UserID               uint64
	UserAccount          string
	UserNickname         sql.NullString
	UserAvatar           sql.NullString
	UserState            uint8
	UserMobile           sql.NullString
	UserGender           sql.NullInt16
	UserBirthday         sql.NullTime
	UserEmail            sql.NullString
	UserIsAuthentication uint8
}

func (q *Queries) AddUserHaveUserId(ctx context.Context, arg AddUserHaveUserIdParams) (sql.Result, error) {
	return q.db.ExecContext(ctx, addUserHaveUserId,
		arg.UserID,
		arg.UserAccount,
		arg.UserNickname,
		arg.UserAvatar,
		arg.UserState,
		arg.UserMobile,
		arg.UserGender,
		arg.UserBirthday,
		arg.UserEmail,
		arg.UserIsAuthentication,
	)
}

const editUserByUserId = `-- name: EditUserByUserId :execresult
UPDATE ` + "`" + `user_info_001` + "`" + `
SET user_nickname = ?, user_avatar = ?, user_mobile = ?,
    user_gender = ?, user_birthday = ?, user_email = ?, 
    updated_at = NOW()
WHERE user_id = ? AND user_is_authentication = 1
`

type EditUserByUserIdParams struct {
	UserNickname sql.NullString
	UserAvatar   sql.NullString
	UserMobile   sql.NullString
	UserGender   sql.NullInt16
	UserBirthday sql.NullTime
	UserEmail    sql.NullString
	UserID       uint64
}

func (q *Queries) EditUserByUserId(ctx context.Context, arg EditUserByUserIdParams) (sql.Result, error) {
	return q.db.ExecContext(ctx, editUserByUserId,
		arg.UserNickname,
		arg.UserAvatar,
		arg.UserMobile,
		arg.UserGender,
		arg.UserBirthday,
		arg.UserEmail,
		arg.UserID,
	)
}

const findUsers = `-- name: FindUsers :many
SELECT user_id, user_account, user_nickname, user_avatar, user_state, user_mobile, user_gender, user_birthday, user_email, user_is_authentication, created_at, updated_at FROM user_info_001 
WHERE user_account LIKE ? OR user_nickname LIKE ?
`

type FindUsersParams struct {
	UserAccount  string
	UserNickname sql.NullString
}

func (q *Queries) FindUsers(ctx context.Context, arg FindUsersParams) ([]UserInfo001, error) {
	rows, err := q.db.QueryContext(ctx, findUsers, arg.UserAccount, arg.UserNickname)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	var items []UserInfo001
	for rows.Next() {
		var i UserInfo001
		if err := rows.Scan(
			&i.UserID,
			&i.UserAccount,
			&i.UserNickname,
			&i.UserAvatar,
			&i.UserState,
			&i.UserMobile,
			&i.UserGender,
			&i.UserBirthday,
			&i.UserEmail,
			&i.UserIsAuthentication,
			&i.CreatedAt,
			&i.UpdatedAt,
		); err != nil {
			return nil, err
		}
		items = append(items, i)
	}
	if err := rows.Close(); err != nil {
		return nil, err
	}
	if err := rows.Err(); err != nil {
		return nil, err
	}
	return items, nil
}

const getUser = `-- name: GetUser :one
SELECT
    user_id, 
    user_account, 
    user_nickname, 
    user_avatar, 
    user_state, 
    user_mobile, 
    user_gender, 
    user_birthday, 
    user_email, 
    user_is_authentication, 
    created_at, 
    updated_at
FROM ` + "`" + `user_info_001` + "`" + `
WHERE user_id = ? LIMIT 1
`

func (q *Queries) GetUser(ctx context.Context, userID uint64) (UserInfo001, error) {
	row := q.db.QueryRowContext(ctx, getUser, userID)
	var i UserInfo001
	err := row.Scan(
		&i.UserID,
		&i.UserAccount,
		&i.UserNickname,
		&i.UserAvatar,
		&i.UserState,
		&i.UserMobile,
		&i.UserGender,
		&i.UserBirthday,
		&i.UserEmail,
		&i.UserIsAuthentication,
		&i.CreatedAt,
		&i.UpdatedAt,
	)
	return i, err
}

const getUsers = `-- name: GetUsers :many
SELECT 
    user_id, 
    user_account, 
    user_nickname, 
    user_avatar, 
    user_state, 
    user_mobile, 
    user_gender, 
    user_birthday, 
    user_email,
    user_is_authentication,
    created_at,
    updated_at
FROM ` + "`" + `user_info_001` + "`" + `
WHERE user_id IN (?)
`

func (q *Queries) GetUsers(ctx context.Context, userID uint64) ([]UserInfo001, error) {
	rows, err := q.db.QueryContext(ctx, getUsers, userID)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	var items []UserInfo001
	for rows.Next() {
		var i UserInfo001
		if err := rows.Scan(
			&i.UserID,
			&i.UserAccount,
			&i.UserNickname,
			&i.UserAvatar,
			&i.UserState,
			&i.UserMobile,
			&i.UserGender,
			&i.UserBirthday,
			&i.UserEmail,
			&i.UserIsAuthentication,
			&i.CreatedAt,
			&i.UpdatedAt,
		); err != nil {
			return nil, err
		}
		items = append(items, i)
	}
	if err := rows.Close(); err != nil {
		return nil, err
	}
	if err := rows.Err(); err != nil {
		return nil, err
	}
	return items, nil
}

const listUsers = `-- name: ListUsers :many
SELECT user_id, user_account, user_nickname, user_avatar, user_state, user_mobile, user_gender, user_birthday, user_email, user_is_authentication, created_at, updated_at FROM user_info_001 LIMIT ? OFFSET ?
`

type ListUsersParams struct {
	Limit  int32
	Offset int32
}

func (q *Queries) ListUsers(ctx context.Context, arg ListUsersParams) ([]UserInfo001, error) {
	rows, err := q.db.QueryContext(ctx, listUsers, arg.Limit, arg.Offset)
	if err != nil {
		return nil, err
	}
	defer rows.Close()
	var items []UserInfo001
	for rows.Next() {
		var i UserInfo001
		if err := rows.Scan(
			&i.UserID,
			&i.UserAccount,
			&i.UserNickname,
			&i.UserAvatar,
			&i.UserState,
			&i.UserMobile,
			&i.UserGender,
			&i.UserBirthday,
			&i.UserEmail,
			&i.UserIsAuthentication,
			&i.CreatedAt,
			&i.UpdatedAt,
		); err != nil {
			return nil, err
		}
		items = append(items, i)
	}
	if err := rows.Close(); err != nil {
		return nil, err
	}
	if err := rows.Err(); err != nil {
		return nil, err
	}
	return items, nil
}

const removeUser = `-- name: RemoveUser :exec
DELETE FROM user_info_001 WHERE user_id = ?
`

func (q *Queries) RemoveUser(ctx context.Context, userID uint64) error {
	_, err := q.db.ExecContext(ctx, removeUser, userID)
	return err
}
