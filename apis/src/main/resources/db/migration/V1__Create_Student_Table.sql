CREATE TABLE student (
  id VARCHAR(255),
  name VARCHAR(255) NOT NULL,
  gender VARCHAR(10) NOT NULL,
  date_of_birth TIMESTAMP NOT NULL,

  CONSTRAINT student_id_pk PRIMARY KEY(id)
)