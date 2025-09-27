mod config;
mod models;
mod services;
mod routes;

use actix_web::{App, HttpServer};
use env_logger::Env;

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    // Initialize logger
    env_logger::Builder::from_env(Env::default().default_filter_or("info")).init();

    let cfg = config::Config::from_env();

    log::info!("Starting server at http://{}:{}", cfg.host, cfg.port);

    HttpServer::new(|| {
        App::new()
            .configure(routes::book::init) // register book routes
    })
        .bind((cfg.host.as_str(), cfg.port))?
        .run()
        .await
}