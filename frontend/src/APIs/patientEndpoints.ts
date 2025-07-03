const patientUrl = import.meta.env.VITE_PATIENT_URL

export const registerPatientDetailsEndpoint = `${patientUrl}/patient/register`
export const getPatientProfile = `${patientUrl}/patient/profile`