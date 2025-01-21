CREATE TABLE book( 		-- 테이블의 정의
	id					BIGSERIAL PRIMARY KEY NOT NULL,		-- id 필드를 기본 키로 선언
	author				varchar(255) NOT NULL,
	isbn				varchar(255) UNIQUE NOT NULL,				-- isbn 필드응 고유한 값이어야 한다는 제약 조건
	price				float8 NOT NULL,
	title				varchar(255) NOT NULL,
	created_date		timestamp NOT NULL,
	last_modified_date	timestamp NOT NULL,
	version 			integer NOT NULL
);