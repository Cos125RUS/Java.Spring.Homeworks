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
            if (event.target != event.target.parentNode.querySelector('.checkbox_label') && event.target != event.target.parentNode.querySelector('.checkbox')) {
                const row = event.target.parentNode;
                const id = row.querySelector('.checkbox').id.replace('id_', '');
                const url = `/site/add/${id}`;
                window.location.href = url;
            }
        });
    });

    const deleteButton = document.getElementById('delete');
    deleteButton.addEventListener('click', () => {
        const deleteList = [];
        selectedNodes.forEach(element => {
            const id = element.querySelector('.checkbox').id.replace('id_', '');
            const url = `/notes/${id}`;
            let req = new XMLHttpRequest();
            req.open("DELETE", url);
            req.send(null);
            element.parentNode.removeChild(element);
            deleteList.push(element);
        });

        deleteList.forEach(element => {
            selectedNodes.pop(element);
        });
    });
}

working();