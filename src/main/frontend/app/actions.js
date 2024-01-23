'use server'

import {addNote, updateNote, deleteNote} from '@/lib/server';
import {redirect} from "next/navigation";
import {revalidatePath} from "next/cache";

import { z } from "zod";
const schema = z.object({
    title: z.string(),
    content: z.string().min(1, '请填写内容').max(100, '字数最多 100')
});

export  async function updateAction(prevState, formData) {
    const noteId = formData.get('noteId')
    const title = formData.get('title')
    const content = formData.get('body')
    const note = {
        noteId,
        title,
        content
    }


    const validated = schema.safeParse(note)
    if (!validated.success) {
        return {
            errors: validated.error.issues,
        }
    }

    if (note.noteId) {
        updateNote(note)
        revalidatePath('/', 'layout')
        redirect(`/note/${note.noteId}`)
    } else {
        const res = await addNote(note)
        revalidatePath('/', 'layout')
        redirect(`/note/${res}`)
    }
    return { message: `Add Success!` }
}

export async function deleteAction(prevState, formData) {
    const noteId = formData.get('noteId')
    deleteNote(noteId)
    revalidatePath('/', 'layout')
    redirect('/')
}
