CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY ,
    username VARCHAR(50) NOT NULL UNIQUE ,
    email VARCHAR(50) NOT NULL UNIQUE ,
    password VARCHAR(50) NOT NULL ,
    enabled BOOLEAN NOT NULL DEFAULT true ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    updated_at TIMESTAMP
);

CREATE TABLE roles (
    role_id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(50) NOT NULL UNIQUE ,
    description VARCHAR(100) NOT NULL ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE user_role (
    user_id BIGINT NOT NULL ,
    role_id BIGINT NOT NULL ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    updated_at TIMESTAMP ,
    PRIMARY KEY (user_id, role_id) ,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_roles_name ON roles(name);

INSERT INTO roles (name, description) VALUES
('PATIENT', 'Standard user role'),
('DOCTOR', 'Doctor user role'),
('HOSPITAL_ADMIN', 'Hospital administrative user role'),
('SUPER_ADMIN', 'Super administrative user role');

