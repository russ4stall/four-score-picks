
CREATE TABLE user (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  is_admin bit NOT NULL default 0,
  registered_on datetime NOT NULL,
  last_login_on datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE game (
  id int NOT NULL AUTO_INCREMENT,
  away_team int NOT NULL,
  home_team int NOT NULL,
  week int NOT NULL,
  game_time datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE team (
  id int NOT NULL,
  name int NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pick (
  user_id int NOT NULL,
  game_id int NOT NULL,
  pick int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE news (
  id int NOT NULL AUTO_INCREMENT,
  news TEXT NOT NULL,
  user_id int NOT NULL,
  date_posted datetime NOT NULL,
  PRIMARY KEY (id)
  );

CREATE TABLE comments (
  id int NOT NULL AUTO_INCREMENT,
  comment_text TEXT NOT NULL,
  user_id int NOT NULL,
  date_posted datetime NOT NULL,
  PRIMARY KEY (id)
  );

CREATE TABLE hot_game (
  week int NOT NULL,
  game_id int NOT NULL
  );

CREATE TABLE result (
  game_id int NOT NULL,
  winner_id int NOT NULL,
  home_team_score int NOT NULL,
  away_team_score int NOT NULL
  );

