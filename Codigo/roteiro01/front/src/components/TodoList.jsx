import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import React from 'react';

export const TodoList = ({ task, deleteTodo }) => {
    return (
        <div className="Todo">
            <p className={`${task.completed ? "completed" : "incompleted"}`}>
                {task.description}
            </p>
            <button onClick={() => deleteTodo(task.id)}>
                <FontAwesomeIcon icon={faTrash} />
            </button>
            <button>
                <FontAwesomeIcon className="edit-icon" icon={faPenToSquare} />
            </button>
        </div>
    );
};
