use crate::models::book::Book;

use std::sync::atomic::{AtomicU32, Ordering};
use std::time::{SystemTime, UNIX_EPOCH};

static COUNTER: AtomicU32 = AtomicU32::new(0);
const AUTHORS: [&str; 5] = ["Josh", "Alex", "Bob", "Richard", "Kevin"];
const MAX_BOOKS: u32 = 1000;

pub fn get_book(id: u32) -> Option<Book> {
    if id == 0 || id > MAX_BOOKS {
        return None;
    }
    Some(generate_book(id))
}

pub fn get_books(page: u32, page_size: u32) -> Vec<Book> {
    let page = page.max(1);
    let start = (page - 1) * page_size + 1;
    let end = (start + page_size - 1).min(MAX_BOOKS);

    (start..=end).map(generate_book).collect()
}

fn generate_book(id: u32) -> Book {
    let counter_value = COUNTER.fetch_add(1, Ordering::SeqCst) + 1;

    // Equivalent of System.currentTimeMillis()
    let millis = SystemTime::now()
        .duration_since(UNIX_EPOCH)
        .expect("Time went backwards")
        .as_millis();

    let title = format!("Title-{}-{}", millis % 100, counter_value);
    let isbn = format!("ISBN-{}-{}", millis % 1000, counter_value);

    let author_index = (counter_value as usize) % AUTHORS.len();
    let author = format!("{}-{}", AUTHORS[author_index], counter_value);

    Book {
        id, // use requested id here
        title,
        author,
        isbn,
    }
}