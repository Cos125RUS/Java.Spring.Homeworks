function working() {
    const notesList = document.querySelectorAll('.note_row');
    const selectedNodes = [];
    notesList.forEach(row => {
        const checkboxLabel = row.querySelector('.checkbox_label');
        checkboxLabel.addEventListener('click', event => {
            const row = event.target.parentNode.parentNode;
            const checkbox = event.target.previousElementSibling;
            if (!checkbox.checked) {
                selectedNodes.push(row);
            } else selectedNodes.pop(row);
        });

        row.addEventListener('click', event => {
            const row = event.target.parentNode;
            // TODO переход на страницу редактирования заметки
        });
    });

    const deleteButton = document.getElementById('delete');
    deleteButton.addEventListener('click', () => {
        selectedNodes.forEach(element => {
            const id = element.querySelector('.checkbox').id.replace('id_', '');
            const url = `/${id}`;

            // let deleteRequest = URLRequest.delete(element)

            let req = new XMLHttpRequest();
            req.open("DELETE", url);
            req.send(null);
            console.log("send request");
        });
    });
}

working();