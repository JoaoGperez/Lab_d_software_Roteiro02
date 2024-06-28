import React, { useState, useEffect } from 'react';
import { TodoForm } from './TodoForm';
import { TodoList } from './TodoList';
import { getTodos, createTodo, updateTodo, deleteTodo } from './todoService';
import './TodoWrapper.css';

export const TodoWrapperService = () => {
    const [todos, setTodos] = useState([]);

    useEffect(() => {
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        try {
            const response = await getTodos();
            setTodos(response.data);
        } catch (error) {
            console.error("Error fetching todos:", error);
        }
    };

    const addTodo = async (description, dueDate, priority) => {
        const newTodo = {
            description,
            dueDate,
            priority,
            completed: false,
        };
        try {
            await createTodo(newTodo);
            fetchTodos();
        } catch (error) {
            console.error("Error adding todo:", error);
        }
    };

    const handleDeleteTodo = async (id) => {
        try {
            await deleteTodo(id);
            fetchTodos();
            console.log("Tarefa deletada");
        } catch (error) {
            console.error("Error deleting todo:", error);
        }
    };

    const handleToggleComplete = async (id) => {
        const todo = todos.find((todo) => todo.id === id);
        try {
            await updateTodo(id, { ...todo, completed: !todo.completed });
            fetchTodos();
        } catch (error) {
            console.error("Error toggling complete:", error);
        }
    };

    const handleEditTodo = async (id, updatedTodo) => {
        try {
            await updateTodo(id, updatedTodo);
            fetchTodos();
        } catch (error) {
            console.error("Error editing todo:", error);
        }
    };

    return (
        <div className='TodoWrapper'>
            <h1>Lista de Tarefas! (Service)</h1>
            <TodoForm addTodo={addTodo} />
            {todos.map((todo) => (
                <TodoList
                    key={todo.id}
                    task={todo}
                    deleteTodo={handleDeleteTodo}
                    editTodo={handleEditTodo}
                    toggleComplete={handleToggleComplete}
                />
            ))}
        </div>
    );
};
