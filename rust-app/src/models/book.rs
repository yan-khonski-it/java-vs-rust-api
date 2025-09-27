use serde::Serialize;

#[derive(Serialize, Clone)]
pub struct Book {
    pub id: u32,
    pub title: String,
    pub author: String,
    pub isbn: String,
}