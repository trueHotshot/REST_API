$(function () {
    $newCard = $('<div class="card card-register mx-auto mt-5">').append($('<div class="card-header">');
    $bookListContainer = $newCard.clone().firstElementChild.text('List of books');
    $form = $newCard.clone().firstElementChild.text('Add new book');
    $form.append($('<div class="card-body">').append($('<form id="contact_form">')));
    $bookList = $("<ul id='#bookList'>");

    $bookListContainer.append($('<div class="card-body">').prepend($bookList));

    $("body").find(".container").prepend($bookListContainer);
    $("body").find(".container").append($form);

    getBooks();

    function request(onDone, bookId, method, data, dataType) {
        var BASE_URL = 'http://localhost:8080/books/';
        var url = bookId ? BASE_URL + bookId : BASE_URL;
        $.ajax({
            url: url,
            method: method || 'GET',
            data: JSON.stringify(data) || null,
            dataType: dataType || null,
            contentType: 'application/json',
            success: onDone
        })
    }

    function getBooks() {
        function onDone(response) {
            response.forEach(function (book) {
                var $li = $('<li>');
                var $span = $('<span>');
                var $div = $('<div>').hide();
                var $deleteButton = $('<input type="submit" value="Delete">');
                $span.text(book.title).attr('data-id', book.id);
                $li.append($span).append($deleteButton).append($div);
                $bookList.append($li)
            })
        }
        request(onDone)
    }

    function getBook(bookId, onDone) {
        request(onDone, bookId)
    }

    function addBook(data, onDone) {
        request(onDone, null, 'POST', data, "json")
    }

    function removeBook(bookId, onDone) {
        request(onDone, bookId, "DELETE")
    }

    var $formAdd = $("#contact_form");
    $formAdd.on('click', '#submit', function (event) {
        event.preventDefault();

        function onDone() {
            location.reload(true)
        }

        var data = {
            'isbn': $('#isbn').val(),
            'title': $('#title').val(),
            'author': $('#author').val(),
            'publisher': $('#publisher').val(),
            'type': $('#type').val()
        };
        addBook(data, onDone())
    });

    $bookList.on('click', 'input', function () {
        var $buttonClicked = $(this);
        var bookId = $buttonClicked.prev().data('id');

        function onDone() {
            $buttonClicked.parent().remove()
        }
        removeBook(bookId, onDone)
    });

    $bookList.on('click', 'span', function () {
        var $book = $(this);
        var bookId = $book.data('id');
        var $description = $book.next().next();
        if ($description.text() === "") {
            function onDone(book) {
                var info = [
                    book.author,
                    book.isbn,
                    book.publisher,
                    book.type
                ];

                info.forEach(function (value) {
                    var $p = $('<p>').text(value);
                    $description.append($p)
                })
            }

            getBook(bookId, onDone)
        }
        $description.slideToggle();
    })
});