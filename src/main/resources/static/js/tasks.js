const addSaveTaskHandler = () => {
    Array.from(document.getElementsByClassName('save-task')).forEach(function (saveButton) {
        hideActionButton(saveButton);
        Array.from(saveButton.parentElement.getElementsByTagName('input')).forEach(function (checkbox) {
            checkbox.onclick = function (event) {
                const formAction = saveButton.formAction;
                const formMethod = saveButton.parentElement.method;
                const formData = new FormData(saveButton.parentElement);
                checkbox.nextElementSibling.className = event.target.checked ? 'checked' : '';
                const request = new XMLHttpRequest();
                request.open(formMethod, formAction, true);
                request.send(formData);
                updateTaskList();
                updateIncompleteCount();
            };
        });
    });
};

const addFilterTasksHandler = () => {
    const filterButton = document.getElementById('filterTasks');
    const completedFilter = getCompletedFilter();
    hideActionButton(filterButton);
    completedFilter.onclick = function () {
        updateTaskList();
    }
};

const updateTaskList = () => {
    const completedFilter = getCompletedFilter();
    const hideCompleted = completedFilter.checked;
    const tasks = getTasksList();
    for (let i = 0; i < tasks.length; ++i) {
        const checkbox = getTaskCheckBox(tasks[i]);
        if (checkbox !== undefined && checkbox.checked === true) {
            tasks[i].hidden = hideCompleted;
        }
    }
};

const updateIncompleteCount = () => {
    let count = 0;
    const tasks = getTasksList();
    for (let i = 0; i < tasks.length; ++i) {
        const checkbox = getTaskCheckBox(tasks[i]);
        if (checkbox !== undefined && checkbox.checked === false) {
            count++;
        }
    }
    const incompleteCount = document.getElementById('taskCount');
    incompleteCount.textContent = `${count}`;
};

const getCompletedFilter = () => {
    return  document.getElementById('hideCompleted');
};

const getTasksList = () => {
    const taskList = document.getElementById('taskList');
    return taskList.getElementsByTagName("li");
};

const getTaskCheckBox = (task) => {
    const taskFields = task.getElementsByTagName('input');
    return Array.from(taskFields).filter(input => input.name === 'done')[0];
};

const hideActionButton = (element) => {
    element.style.display = 'none';
    element.onclick = function (event) {
        event.preventDefault();
    };
};

window.addEventListener('load', addSaveTaskHandler, false );
window.addEventListener('load', addFilterTasksHandler, false );
