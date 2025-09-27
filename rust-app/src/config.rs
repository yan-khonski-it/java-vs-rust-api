use std::env;

pub struct Config {
    pub host: String,
    pub port: u16,
}

impl Config {
    pub fn from_env() -> Self {
        let host = env::var("APP_HOST").unwrap_or_else(|_| "0.0.0.0".to_string());
        let port = env::var("APP_PORT").unwrap_or_else(|_| "8081".to_string())
            .parse()
            .expect("APP_PORT must be a valid number");

        Config { host, port }
    }
}