import React, { useState } from 'react';
import { TodoForm } from './TodoForm';
import { TodoList } from './TodoList';
import { v4 as uuidv4 } from "uuid";
import './TodoWrapper.css';

export const TodoWrapper = () => {

    const [todos, setTodos] = useState([
        { id: 1, description: 'Tarefa exemplo', completed: false, dueDate: '2024-07-01', priority: 'Medium' }
    ]);

    const addTodo = (description, dueDate, priority) => {
        setTodos([
            ...todos,
            { id: uuidv4(), description, completed: false, dueDate: dueDate, priority: priority },
        ]);
    }

    const deleteTodo = (id) => setTodos(todos.filter((todo) => todo.id !== id));

    const toggleComplete = id => {
        const newTodos = todos.map(todo => todo.id === id ? {
            ...todo, completed:
                !todo.completed
        } : todo);
        setTodos(newTodos);
        localStorage.setItem('todos', JSON.stringify(newTodos));
    }

    const editTodo = (id) => {
        setTodos(
            todos.map((todo) =>
                todo.id === id ? { ...todo, isEditing: !todo.isEditing } : todo
            )
        );
    }

    return (
        <div className='TodoWrapper'>
            <h1>Lista de Tarefas</h1>
            <TodoForm addTodo={addTodo} />
            {todos.map((item) =>
                <TodoList
                    key={item.id}
                    task={item}
                    editTodo={editTodo}
                    deleteTodo={deleteTodo}
                    toggleComplete={toggleComplete}

                />
            )
            }
        </div>
    );
}