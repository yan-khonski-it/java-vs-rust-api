use actix_web::{get, web, HttpResponse, Responder};
use crate::models::book::Book;
use crate::services::book_service;

#[get("/api/books/{id}")]
async fn get_book(path: web::Path<u32>) -> impl Responder {
    match book_service::get_book(path.into_inner()) {
        Some(book) => HttpResponse::Ok().json(book),
        None => HttpResponse::NotFound().body("Book not found"),
    }
}

#[get("/api/books")]
async fn get_books(query: web::Query<Pagination>) -> impl Responder {
    let books: Vec<Book> = book_service::get_books(query.page, query.page_size);
    HttpResponse::Ok().json(books)
}

#[derive(serde::Deserialize)]
struct Pagination {
    page: u32,
    page_size: u32,
}

pub fn init(cfg: &mut web::ServiceConfig) {
    cfg.service(get_book).service(get_books);
}