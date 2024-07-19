INSERT INTO article(title,content) VALUES('aa', '111');
INSERT INTO article(title, content) VALUES('bb', '222');
INSERT INTO article(title, content) VALUES('cc', '333');

INSERT INTO article(title,content) VALUES('당신의 인생영화는?', '댓글고');
INSERT INTO article(title, content) VALUES('당신의 소울푸드는?', '댓글고고');
INSERT INTO article(title, content) VALUES('당신의 취미는?', '댓글고고고');

INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Park', '굿 윌 헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Kim', '아이엠샘');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Choi', '쇼 생크 탈출');

INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Park', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Kim', '샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Choi', '초밥');

INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Park', '조깅');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Kim', '유튜브 시청');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Choi', '독서');