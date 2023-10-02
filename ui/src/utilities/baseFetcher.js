export const getBaseURL = () => {
    return window?.location?.origin;
    // return "http://localhost"
}

export const authFetcher = async (url, conf, contentType) => {
    const path =  `${getBaseURL()}/${url}`;
    const authToken = sessionStorage.getItem('auth-token')
    const response = await fetch(path, {
        headers: {
            'Auth-Token': authToken,
            "Content-Type": contentType ?? "application/json",
        },
        ...(conf && conf),
    });
    return response;
}

export const publicFetcher = async (url, conf) => {
    const path = `${getBaseURL()}/${url}`;
    const response = await fetch(path, {
        headers: {
            "Content-Type": "application/json",
        },
        ...(conf && conf)
    });
    return response;
}

export const imageFetcher = (url) => {
    return `${getBaseURL()}/${url}`;
}