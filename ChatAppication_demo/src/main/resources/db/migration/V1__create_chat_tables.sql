-- ១. បង្កើត Table users (ប្រើឈ្មោះ users ដើម្បីកុំឱ្យទាស់នឹង Reserved Keyword របស់ Postgres)
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY, -- BIGSERIAL សម្រាប់បង្កើតលេខសម្គាល់ Auto-increment ក្នុង Postgres
    username VARCHAR(255) NOT NULL UNIQUE,
    userpassword VARCHAR(255) NOT NULL,
    useremail VARCHAR(255) NOT NULL UNIQUE,
    phonenumber VARCHAR(50),
    role VARCHAR(50) NOT NULL
);

-- ២. បង្កើត Table chat_rooms (បន្ទប់ឆាតក្រុម)
CREATE TABLE chat_rooms (
    id BIGSERIAL PRIMARY KEY,
    room_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ៣. បង្កើត Table chat_messages (ប្រវត្តិសារ)
CREATE TABLE chat_messages (
    id BIGSERIAL PRIMARY KEY,
    room_id BIGINT NOT NULL,
    sender_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- បង្កើតការភ្ជាប់ទំនាក់ទំនង Foreign Key ទៅកាន់ Table ទាំងពីរខាងលើ
    CONSTRAINT fk_message_room FOREIGN KEY (room_id) REFERENCES chat_rooms(id) ON DELETE CASCADE,
    CONSTRAINT fk_message_sender FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE
);
