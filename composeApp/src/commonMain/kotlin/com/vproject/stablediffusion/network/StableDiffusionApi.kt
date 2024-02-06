package com.vproject.stablediffusion.network

/**
 * API interface for Stable Diffusion Network API
 */
interface StableDiffusionApi {
    /**
     * Method to request generates and returns an image from a prompt API.
     *
     * @param prompt Text prompt with description of the things you want in the image to be generated.
     * @param negativePrompt Items you don't want in the image.
     *
     * @return text to image response body.
     */
    suspend fun postTextToImage(prompt: String, negativePrompt: String): String

    /**
     * Method to request queued images from stable diffusion API.
     * Usually more complex image generation requests take more time for processing.
     * Such requests are being queued for processing and the output images are retrievable after some time.
     *
     * @param id The ID returned together with the image URL in the response upon its generation.
     *
     * @return queued image response body.
     */
    suspend fun fetchQueuedImage(id: String): String
}