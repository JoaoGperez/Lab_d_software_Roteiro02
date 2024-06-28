import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faPenToSquare } from '@fortawesome/free-solid-svg-icons';

export const TodoList = ({ task, deleteTodo, editTodo, toggleComplete }) => {
    return (
        <div className="Todo">
            <p 
                className={`${task.completed ? "completed" : "incompleted"}`} 
                onClick={() => toggleComplete(task.id)}
            >
                {task.description}
            </p>
            <div className="todo-buttons">
                <button onClick={() => deleteTodo(task.id)}>
                    <FontAwesomeIcon icon={faTrash} />
                </button>
                <button onClick={() => editTodo(task.id)}>
                    <FontAwesomeIcon className="edit-icon" icon={faPenToSquare} />
                </button>
            </div>
        </div>
    );
};
