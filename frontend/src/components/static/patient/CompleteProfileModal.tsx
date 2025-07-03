import { useState } from "react"
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
    DialogFooter,
} from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import {
    Select,
    SelectTrigger,
    SelectValue,
    SelectContent,
    SelectItem,
} from "@/components/ui/select"
import { Button } from "@/components/ui/button"

type CompleteProfileModalProps = {
    onSubmit: (data: {
        age: string
        bloodGroup: string
        emergencyContact: string
        allergies: string
    }) => void
}

const bloodGroupMap: Record<"A+" | "A-" | "B+" | "B-" | "AB+" | "AB-" | "O+" | "O-", string> = {
    "A+": "A_POSITIVE",
    "A-": "A_NEGATIVE",
    "B-": "B_NEGATIVE",
    "B+": "B_POSITIVE",
    "AB+": "AB_POSITIVE",
    "AB-": "AB_NEGATIVE",
    "O-": "O_NEGATIVE",
    "O+": "O_POSITIVE",
}

const CompleteProfileModal = ({ onSubmit }: CompleteProfileModalProps) => {
    const [form, setForm] = useState({
        age: "",
        bloodGroup: "",
        emergencyContact: "",
        allergies: "",
    })

    const handleChange = (key: string, value: string) => {
        if (key === "bloodGroup") {
            setForm((prev) => ({
                ...prev,
                [key]: bloodGroupMap[value as keyof typeof bloodGroupMap] || ""
            }))
            return
        }

        setForm((prev) => ({ ...prev, [key]: value }))
    }

    return (
        <Dialog>
            <DialogTrigger asChild>
                <Button variant="default" className="w-fit mt-2">
                    Complete Profile
                </Button>
            </DialogTrigger>

            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Complete Your Profile</DialogTitle>
                </DialogHeader>

                <div className="grid gap-4 py-4">
                    <div>
                        <Label htmlFor="age">Age</Label>
                        <Input
                            id="age"
                            type="number"
                            value={form.age}
                            onChange={(e) => handleChange("age", e.target.value)}
                        />
                    </div>

                    <div>
                        <Label htmlFor="bloodGroup">Blood Group</Label>
                        <Select
                            onValueChange={(value) => handleChange("bloodGroup", value)}
                            value={form.bloodGroup}
                        >
                            <SelectTrigger id="bloodGroup">
                                <SelectValue placeholder="Select Blood Group" />
                            </SelectTrigger>
                            <SelectContent>
                                {["A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"].map((group) => (
                                    <SelectItem key={group} value={group}>
                                        {group}
                                    </SelectItem>
                                ))}
                            </SelectContent>
                        </Select>
                    </div>

                    <div>
                        <Label htmlFor="emergencyContact">Emergency Contact</Label>
                        <Input
                            id="emergencyContact"
                            value={form.emergencyContact}
                            onChange={(e) => handleChange("emergencyContact", e.target.value)}
                        />
                    </div>

                    <div>
                        <Label htmlFor="allergies">Allergies</Label>
                        <Input
                            id="allergies"
                            value={form.allergies}
                            onChange={(e) => handleChange("allergies", e.target.value)}
                        />
                    </div>
                </div>

                <DialogFooter>
                    <Button
                        className="bg-teal-500 hover:bg-teal-600 text-white"
                        onClick={() => onSubmit(form)}
                    >
                        Save
                    </Button>
                </DialogFooter>
            </DialogContent>
        </Dialog>
    )
}

export default CompleteProfileModal
