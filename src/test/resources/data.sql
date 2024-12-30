CREATE TABLE irregular_verbs (
                                 id SERIAL PRIMARY KEY,
                                 base_form VARCHAR(50) NOT NULL,
                                 past_simple VARCHAR(50) NOT NULL,
                                 past_participle VARCHAR(50) NOT NULL,
                                 definition TEXT
);

CREATE INDEX idx_irregular_verbs_id ON irregular_verbs(id);

INSERT INTO irregular_verbs (base_form, past_simple, past_participle)
VALUES
    ('be', 'was/were', 'been'),
    ('become', 'became', 'become'),
    ('begin', 'began', 'begun'),
    ('break', 'broke', 'broken'),
    ('bring', 'brought', 'brought'),
    ('build', 'built', 'built'),
    ('buy', 'bought', 'bought'),
    ('catch', 'caught', 'caught'),
    ('choose', 'chose', 'chosen'),
    ('come', 'came', 'come'),
    ('cost', 'cost', 'cost'),
    ('cut', 'cut', 'cut'),
    ('do', 'did', 'done');

CREATE TABLE session (
                         id SERIAL PRIMARY KEY,
                         user_id INT NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_user_id ON session(user_id);

CREATE TABLE session_check (
                               id SERIAL PRIMARY KEY,
                               session_id INT NOT NULL,
                               irregular_verb_id INT NOT NULL,
                               answer_past_simple VARCHAR(255) NOT NULL,
                               answer_past_participle VARCHAR(255) NOT NULL,
                               FOREIGN KEY (session_id) REFERENCES session(id),
                               FOREIGN KEY (irregular_verb_id) REFERENCES irregular_verbs(id)
);

CREATE INDEX idx_session_id ON session_check(session_id);
CREATE INDEX idx_irregular_verb_id ON session_check(irregular_verb_id);

ALTER TABLE session
ALTER COLUMN user_id TYPE VARCHAR(255);

ALTER TABLE session_check
DROP COLUMN answer_past_simple,
DROP COLUMN answer_past_participle;

ALTER TABLE session_check
ADD COLUMN answer_past_simple VARCHAR(255) NULL,
ADD COLUMN answer_past_participle VARCHAR(255) NULL;
