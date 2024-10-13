CREATE TABLE reviews (
                        id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL,
                        movie_id INT NOT NULL,
                        rating INT CHECK (rating >= 1 AND rating <= 5),  -- Ratings between 1 and 5
                        review_text TEXT,
                        username varchar(255) NOT NULL ,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER on_update BEFORE UPDATE
ON reviews FOR EACH ROW EXECUTE PROCEDURE
update_timestamp();


