import {Express} from "express";

export const defaultRouter = (app: Express) => {
    app.get('/api/v1/', (req, res) => {
        res.json({
            message: 'hello world!'
        })
    })
}